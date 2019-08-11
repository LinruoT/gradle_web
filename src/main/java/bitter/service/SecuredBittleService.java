package bitter.service;

import bitter.data.CommentRepository;
import bitter.data.PictureRepository;
import bitter.domain.Comment;
import bitter.domain.Picture;
import bitter.web.error.ImageUploadException;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import bitter.data.BittleRepository;

import bitter.domain.Bittle;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class description
 *
 *
 * @version        Enter version here..., 19/07/29
 * @author         Enter your name here...
 */
@Service
public class SecuredBittleService implements BittleService {
    private BittleRepository bittleRepository;
    private PictureRepository pictureRepository;
    private CommentRepository commentRepository;

    /**
     * Constructs ...
     *
     *
     * @param bittleRepository
     */
    @Autowired
    public SecuredBittleService(BittleRepository bittleRepository,PictureRepository pictureRepository,CommentRepository commentRepository) {
        this.bittleRepository = bittleRepository;
        this.pictureRepository = pictureRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    @Secured({"ROLE_BITTER", "ROLE_ADMIN"})
    public Bittle addBittle(Bittle bittle) {
        System.out.println("有权限add bittle");
        bittleRepository.save(bittle);
        return null;
    }

    @Override
    public Bittle addBittle(Bittle bittle, MultipartFile[] files) {
        // 保存图片
        List<Picture> pictures = new ArrayList<>();
        // 循环获取file数组中得文件
        for (MultipartFile file : files) {
            if (file.getOriginalFilename().contains(".")) {
                String imageName = bittle.getBitter().getUsername() + "_"
                        + new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()) + "_"
                        + file.getOriginalFilename();
                Picture picture = new Picture(imageName, file.getSize(), bittle.getBitter());

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
        bittleRepository.save(bittle);
        return null;
    }

    @Override
    @Secured({"ROLE_BITTER", "ROLE_ADMIN"})
    public boolean forceDeleteBittle(Bittle bittle, String bitterName) {
        try {
            if (bitterName.equals(bittle.getBitter().getUsername())) {
                bittleRepository.delete(bittle.getId());
                System.out.println("forceDeleteBittle: 删除成功");

                return true;
            } else {
                System.out.println("forceDeleteBittle: 认证失败，操作用户" + bitterName + " bittle作者"
                                   + bittle.getBitter().getUsername());

                return false;
            }
        } catch (Exception e) {
            System.out.println("forceDeleteBittle: 删除失败，异常：" + e.getMessage());

            return false;
        }

    }

    @Override
    @Secured({"ROLE_BITTER", "ROLE_ADMIN"})
    public boolean addComment(Bittle bittle, Comment comment) {
        List<Comment> comments;
        try {
            comment = commentRepository.save(comment);
            System.out.println("保存comment成功"+comment.getId());
            if(comment.getId()==null)
                return false;
            comments = bittle.getComments();
            comments.add(comment);
            bittle.setComments(comments);
            bittle=bittleRepository.save(bittle);
            System.out.println("addComment: 成功 bittleId="+bittle.getBitter()+" "+comment);
            return true;
        } catch (Exception e) {
            System.out.println("addComment: 失败，异常：" + e.getMessage());
            return false;
        }
    }

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
}


//~ Formatted by Jindent --- http://www.jindent.com
