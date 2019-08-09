package bitter.web.bitter;


import bitter.data.PictureRepository;
import bitter.domain.Bitter;
import bitter.data.BitterRepository;
import bitter.domain.Picture;
import bitter.web.error.BittleNotFoundException;
import bitter.web.error.DuplicateBitterException;
import bitter.web.error.ImageUploadException;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//处理/bitter的控制器（用户）
@Controller
@RequestMapping("/bitter")
public class BitterController {
    private BitterRepository bitterRepository;
    private PictureRepository pictureRepository;
    @Autowired
    public BitterController(BitterRepository bitterRepository,PictureRepository pictureRepository) { //构造器
        this.bitterRepository=bitterRepository;
        this.pictureRepository=pictureRepository;
    }
//    @RequestMapping(value = "/register",method = RequestMethod.GET) //注册页面
//    public String showRegistration() {
//        return "registerForm";
//    }
    @RequestMapping(value = "/register",method = RequestMethod.GET) //注册页面
    public String showRegistration(Model model) {
        model.addAttribute(new Bitter());//添加key=bitter的属性
        return "registerForm";
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST) //提交表单，bitter的属性会被form同名参数填充
    public String processRegistration(@RequestPart("profilePicture") MultipartFile profilePicture,
                                      @Valid Bitter bitter,
                                      Errors errors,
                                      Model model,
                                      RedirectAttributes redirectAttributes,
                                      HttpServletRequest request) throws Exception {
        if(errors.hasErrors()) { return "registerForm"; }//有错误 则注册页面
        bitter.setPassword(new StandardPasswordEncoder("gaoduanhei").encode(bitter.getPassword()));
        if(bitterRepository.findByUsername(bitter.getUsername())!=null) {
            throw new DuplicateBitterException();
        } else {
            bitter=bitterRepository.save(bitter);
        }
        if(profilePicture.getOriginalFilename().contains(".")) {

            String imageName = bitter.getUsername() + "_" +
                    new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()) + "_" +
                    profilePicture.getOriginalFilename();
            String savePath = request.getServletContext().getRealPath("/");
            System.out.println("savePath: " + savePath);

            //s3保存上传的图片
            try {
                if(saveImage(imageName, profilePicture)) {
                    Picture icon = pictureRepository.save(
                            new Picture(imageName,profilePicture.getSize(),bitter));
                    bitter.setIcon(icon);
                    bitterRepository.save(bitter);
                }
            } catch (Exception e) {
                throw new ImageUploadException();
            }



//            //本地保存上传的图片
//            File file = new File(savePath + "/" + imageName);
//            profilePicture.transferTo(file);
        }
        model.addAttribute("username",bitter.getUsername());
        model.addAttribute("bitterId",bitter.getId());
        redirectAttributes.addFlashAttribute(bitter); //重定向时，相比url模版只能传递String，flash可以传递对象
        redirectAttributes.addAttribute("username",bitter.getUsername());
        return "redirect:/bitter/{username}"; //比直接拼接字符串更加安全，model的其他属性会自动变成查询参数，/bitter/linruotian?bitterId=0
//        return "redirect:/bitter/"+bitter.getUsername();
    }

//    @RequestMapping(value = "/{username}",method = RequestMethod.GET)
//    public String showBitterProfile(@PathVariable String username, Model model) {
//        Bitter bitter=bitterRepository.findByUsername(username);
//        model.addAttribute(bitter);
//        return "profile";
//    }

    @RequestMapping(value = "/{username}/addpic",method = RequestMethod.GET) //注册页面
    public String showAddPicture(Model model,@PathVariable String username) {
        Bitter bitter = bitterRepository.findByUsername(username);
        model.addAttribute(bitter);
        return "addPictureForm";
    }
    @RequestMapping(value = "/{username}/addpic",method = RequestMethod.POST)
    public String addPicture(@PathVariable String username,
                             @RequestPart("upPicture") MultipartFile upPicture,boolean useAsIcon,
                             Model model,
                             HttpServletRequest request) throws Exception {
        Bitter bitter = bitterRepository.findByUsername(username);
        model.addAttribute(bitter);

        if(upPicture.getOriginalFilename().contains(".")) {
            Picture icon;
            String imageName = bitter.getUsername() + "_" +
                    new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()) + "_" +
                    upPicture.getOriginalFilename();
            String savePath = request.getServletContext().getRealPath("/");
            System.out.println("savePath: " + savePath);

            //s3保存上传的图片
            try {
                if(saveImage(imageName, upPicture)) {
                    icon = pictureRepository.save(
                            new Picture(imageName,upPicture.getSize(),bitter));
                    if(useAsIcon) {
                        bitter.setIcon(icon);
                        bitterRepository.save(bitter);
                    }
                }
            } catch (Exception e) {
                throw new ImageUploadException();
            }

//            //本地保存上传的图片
//            File file = new File(savePath + "/" + imageName);
//            upPicture.transferTo(file);
        }

        return "redirect:/bitter/{username}";
    }

    @RequestMapping(value = "/{username}/changepwd",method = RequestMethod.GET)
    public String showChangePwd(Model model,@PathVariable String username) {
        Bitter bitter = bitterRepository.findByUsername(username);
        model.addAttribute(bitter);
        return "changePwdForm";
    }
    @RequestMapping(value = "/{username}/changepwd",method = RequestMethod.POST)
    public String changePwd(@PathVariable String username, String pwd, Principal principal) {
        Bitter bitter = bitterRepository.findByUsername(username);
        if(bitter!=null
                &&pwd.length()>5
                &&username.equals(principal.getName())) {
            bitter.setPassword(new StandardPasswordEncoder("gaoduanhei").encode(pwd));
            bitterRepository.save(bitter);
        }
        else {
            throw new BittleNotFoundException();
        }
        return "redirect:/";
    }

    //用户页面
    @RequestMapping(value = "/{username}",method = RequestMethod.GET)
    public String showBitterProfile(@PathVariable String username, Model model, HttpServletRequest request) {
        if (!model.containsAttribute("bitter")) {
            System.out.println("没有传来bitter属性，从数据库中读取");
            Bitter bitter = bitterRepository.findByUsername(username);
            model.addAttribute(bitter);


            List<Picture> pictures = pictureRepository.findByBitter(bitter);
            System.out.println("pictures in oss: ");
            for (Picture pic:pictures) {
                System.out.println(pic);
            }
            model.addAttribute("pictureList",pictures);//保存在对象存储的链接

//            String imgPath = request.getServletContext().getRealPath("/");
//            System.out.println("imgPath: "+imgPath); //保存在web服务器上的路径
//            List<String> rawImagesList=new ArrayList<>();
//            File file=new File(imgPath);
//            if (file.isDirectory()) {
//                String[] filelist = file.list();
//                for (int i = 0; i < filelist.length; i++) {
//                    File readfile = new File(imgPath + filelist[i]);
//                    if (!readfile.isDirectory()) {
//                        System.out.println(readfile.getName());
//                        rawImagesList.add(readfile.getName());
//                    }
//                }
//            }
//            model.addAttribute("imageList",rawImagesList);//保存在web服务器上的路径
        }
        return "profile";
    }

    //保存用户图片到对象存储
    private boolean saveImage(String imageName,MultipartFile image) throws ImageUploadException {
        try {
            MinioClient minioClient = new MinioClient("http://vm.linruotian.com:9000","billys3","billy11111111");
             if(minioClient.bucketExists("bitter-dev-img")) {
                 System.out.println("bucket already exists.");
             } else {
                 minioClient.makeBucket("bitter-dev-img");
             }
             minioClient.putObject("bitter-dev-img",imageName,image.getInputStream(),image.getSize(),image.getContentType());
             System.out.println("saveImage: s3保存成功");
             return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ImageUploadException();
        }

    }
}
