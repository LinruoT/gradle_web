package bitter.web;


import bitter.domain.Bitter;
import bitter.data.BitterRepository;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//处理/bitter的控制器（用户）
@Controller
@RequestMapping("/bitter")
public class BitterController {
    private BitterRepository bitterRepository;
    @Autowired
    public BitterController(BitterRepository bitterRepository) { //构造器
        this.bitterRepository=bitterRepository;
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
        if(bitterRepository.save(bitter)==null) {
            throw new DuplicateBitterException();
        }
        String imageName = bitter.getUsername()+"_"+
                new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+"_"+
                profilePicture.getOriginalFilename();
        String savePath=request.getServletContext().getRealPath("/");
        System.out.println("savePath: "+savePath);
        try{
            saveImage(imageName,profilePicture);
        } catch (Exception e) {
            throw new ImageUploadException();
        }
          //s3保存上传的图片
        File file= new File(savePath+"/"+imageName);
        profilePicture.transferTo(file); //本地保存上传的图片

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
    @RequestMapping(value = "/{username}",method = RequestMethod.GET)
    public String showBitterProfile(@PathVariable String username, Model model, HttpServletRequest request) {
        if (!model.containsAttribute("bitter")) {
            System.out.println("没有传来bitter属性，从数据库中读取");
            Bitter bitter = bitterRepository.findByUsername(username);
            model.addAttribute(bitter);

            String imgPath = request.getServletContext().getRealPath("/");
            System.out.println("imgPath: "+imgPath);
            List<String> rawImagesList=new ArrayList<>();
            List<String> s3ImagesList=new ArrayList<>();
            File file=new File(imgPath);
            if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(imgPath + filelist[i]);
                    if (!readfile.isDirectory()) {
                        System.out.println(readfile.getName());
                        rawImagesList.add(readfile.getName());
                        s3ImagesList.add("http://vm.linruotian.com:9000/bitter-dev-img/" + filelist[i]);
                    }
                }
            }
            model.addAttribute("s3ImageList",s3ImagesList);
            model.addAttribute("imageList",rawImagesList);
        }
        return "profile";
    }

    private void saveImage(String imageName,MultipartFile image) throws ImageUploadException {
        try {
            MinioClient minioClient = new MinioClient("http://vm.linruotian.com:9000","billys3","billy11111111");
             if(minioClient.bucketExists("bitter-dev-img")) {
                 System.out.println("bucket already exists.");
             } else {
                 minioClient.makeBucket("bitter-dev-img");
             }
             minioClient.putObject("bitter-dev-img",imageName,image.getInputStream(),image.getSize(),image.getContentType());
             System.out.println("saveImage: s3保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ImageUploadException();
        }

    }
}
