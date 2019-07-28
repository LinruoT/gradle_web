package bitter.web.bittle;

import java.security.Principal;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import bitter.alerts.AlertService;

import bitter.data.BitterRepository;
import bitter.data.BittleRepository;
import bitter.data.PictureRepository;

import bitter.domain.Bitter;
import bitter.domain.Bittle;
import bitter.domain.Picture;

import bitter.service.BittleService;

import bitter.web.error.BittleNotFoundException;
import bitter.web.error.DuplicateBittleException;
import bitter.web.error.ImageUploadException;

import io.minio.MinioClient;

//处理/bittles的控制器（推文）

/**
 * Class description
 *
 *
 * @version        Enter version here..., 19/07/28
 * @author         Enter your name here...
 */
@Controller
@RequestMapping("/bittles")
public class BittleController {
    private BittleRepository bittleRepository;
    private BitterRepository bitterRepository;
    private PictureRepository pictureRepository;
    private BittleService bittleService;
    private AlertService alertService;
    private BittleFeedService bittleFeedService;

    /**
     * Constructs ...
     *
     *
     * @param bittleRepository 自动装配
     * @param bitterRepository 自动装配
     * @param pictureRepository 自动装配
     * @param bittleService 自动装配
     * @param alertService 自动装配
     * @param bittleFeedService 自动装配
     */
    @Autowired    // 构造器传入bittles数据，bittleRepository的实现是JdbcBittleRepository
    public BittleController(BittleRepository bittleRepository, BitterRepository bitterRepository,
                            PictureRepository pictureRepository, BittleService bittleService,
                            AlertService alertService, BittleFeedService bittleFeedService) {
        this.bittleRepository = bittleRepository;
        this.bitterRepository = bitterRepository;
        this.pictureRepository = pictureRepository;
        this.bittleService = bittleService;
        this.alertService = alertService;
        this.bittleFeedService = bittleFeedService;
    }

    /**
     *
     * 路径是/bittles/123
     *
     * @param bittleId
     * @param model
     *
     * @return
     */
    @RequestMapping(
        value = "/{bittleId}",
        method = RequestMethod.GET
    )
    public String bittle(@PathVariable Long bittleId, Model model) {
        Bittle bittle = null;

        try {
            bittle = bittleRepository.findOneWithCache(bittleId);
        } catch (Exception e) {
            System.out.println("find bittle error");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        if (bittle == null) {
            throw new BittleNotFoundException();
        }
        model.addAttribute("bittle", bittle);

        return "bittle";
    }

    /**
     * 处理/bittles页面 GET方法
     *
     * 把bittleRepository的列表填充到模型。model是Map （key-value集合）
     * @param model
     * @param max
     * @param count
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String bittles(Model model, @RequestParam(
        value = "max",
        defaultValue = "" + Long.MAX_VALUE
    ) long max,    // 含有两个参数max和count，路径是/bittles?count=20
    @RequestParam(
        value = "count",
        defaultValue = "20"
    ) int count) {
        List<Bittle> bittleList = bittleRepository.findBittles(max, count);    // custom方法，jpa

//      List<Bittle> bittleList=bittleRepository.readAllByIdNotNullOrderByIdDesc(); //spring data
        for (Bittle oneBittle : bittleList) {
            System.out.println(oneBittle.getMessage());
        }
        model.addAttribute("bittleList", bittleList);
        model.addAttribute(new BittleForm());

        return "bittles";
    }

    /**
     * 永久删除bittle
     * 路径是/forcedel/123
     *
     * @param bittleId
     * @param model
     * @param principal
     *
     * @return
     */
    @RequestMapping(
        value = "/forcedel/{bittleId}",
        method = RequestMethod.GET
    )
    public String forceDeleteBittle(@PathVariable Long bittleId, Model model, Principal principal) {
        Bittle bittle = bittleRepository.findOne(bittleId);

        if ((bittle == null) || (principal == null)) {
            System.out.println("未登录 或 bittle不存在" + bittleId);
            model.addAttribute("result", false);

            return "result";
        }
        System.out.println("永久删除bittle：" + bittle.getId());

        boolean result = bittleService.forceDeleteBittle(bittle, principal.getName());    // 这个方法是被spring security保护的

        model.addAttribute("result", result);

        return "result";
    }

    /**
     * 接受表单，新建一个bittle
     *
     *
     * @param bittleForm
     * @param files
     * @param errors
     * @param principal
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String saveBittle(@Valid BittleForm bittleForm, @RequestPart("files") MultipartFile[] files, Errors errors,
                             Principal principal) {
        if (errors.hasErrors()) {
            return "bittles";
        }

        Bitter bitter = bitterRepository.findByUsername(principal.getName());
        Bittle bittle = new Bittle(null,
                                   bitter,
                                   bittleForm.getMessage(),
                                   new Date(),
                                   bittleForm.getLongitude(),
                                   bittleForm.getLatitude());

        bittleService.addBittle(bittle);    // 这个方法是被spring security保护的
        alertService.sendBittleAlert(bittle);

        // 保存图片
        List<Picture> pictures = new ArrayList<>();

        if ((files != null) && (files.length > 0)) {

            // 循环获取file数组中得文件
            for (MultipartFile file : files) {
                if (file.getOriginalFilename().contains(".")) {
                    String imageName = bitter.getUsername() + "_"
                                       + new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()) + "_"
                                       + file.getOriginalFilename();
                    Picture picture = new Picture(imageName, file.getSize(), bitter);

                    pictures.add(picture);
                    System.out.println("成功获取图片：" + picture);

                    // s3保存上传的图片
                    try {
                        if (saveImage(imageName, file)) {
                            pictureRepository.save(picture);
                        }
                    } catch (Exception e) {
                        throw new ImageUploadException();
                    }
                }
            }
            bittle.setPictures(pictures);
        }

        // 保存bittle
        if (bittleRepository.save(bittle).getId() < 10) {
            throw new DuplicateBittleException();
        }
        bittleFeedService.broadcastBittle(bittle);

        return "redirect:/bittles";
    }

    // 已经集中在AppWideExceptionHandler了
//  @ExceptionHandler(DuplicateBittleException.class) //处理此控制器所有方法抛出的DuplicateBittleException异常
//  public String handleDuplicateBittle() {
//      return "error/duplicate";
//
//  }
    // 保存用户图片到对象存储
    private boolean saveImage(String imageName, MultipartFile image) throws ImageUploadException {
        try {
            MinioClient minioClient = new MinioClient("http://vm.linruotian.com:9000", "billys3", "billy11111111");

            if (minioClient.bucketExists("bitter-dev-img")) {
                System.out.println("bucket already exists.");
            } else {
                minioClient.makeBucket("bitter-dev-img");
            }
            minioClient.putObject("bitter-dev-img",
                                  imageName,
                                  image.getInputStream(),
                                  image.getSize(),
                                  image.getContentType());
            System.out.println("saveImage: s3保存成功");

            return true;
        } catch (Exception e) {
            e.printStackTrace();

            throw new ImageUploadException();
        }

    }

    /**
     *
     * 测试restful bitter.web.api.BittleApiController
     *
     * @param bittleId
     * @param model
     *
     * @return
     */
    @RequestMapping(
        value = "/testapi/{bittleId}",
        method = RequestMethod.GET
    )
    public String testApiBittle(@PathVariable Long bittleId, Model model) {
        System.out.println("testapi: /bittles/testapi/{bittleId}");

        Bittle bittle;
        RestTemplate restTemplate = new RestTemplate();

//      bittle = restTemplate.getForObject("http://localhost:8080/bittles/{bittle}",
        bittle = restTemplate.getForObject("http://b.linruotian.com/bittles/{bittle}", Bittle.class, bittleId);
        if (bittle == null) {
            throw new BittleNotFoundException();
        }
        model.addAttribute("bittle", bittle);

        return "bittle";
    }

    /**
     * Method description
     *
     *
     * @param bittleForm
     *
     * @return
     */
    @RequestMapping(
        value = "/testapi",
        method = RequestMethod.POST
    )
    public String testApiSaveBittle(BittleForm bittleForm) {
        System.out.println("testapi: POST /bittles/testapi");

        RestTemplate restTemplate = new RestTemplate();
        Bitter bitter = bitterRepository.findByUsername("billy");
        Bittle bittle = new Bittle(null,
                                   bitter,
                                   bittleForm.getMessage(),
                                   new Date(),
                                   bittleForm.getLongitude(),
                                   bittleForm.getLatitude());

        return restTemplate.postForLocation("http://b.linruotian.com/bittles", bittle).toString();
    }

    // POST API还存在问题 （是csrf的引起的，关闭可以解决）

    /**
     * Method description
     *
     *
     * @param model
     *
     * @return
     */
    @RequestMapping(
        value = "/testapi",
        method = RequestMethod.GET
    )
    public String testapiBittleForm(Model model) {
        model.addAttribute(new BittleForm());

        return "testapibittle";
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
