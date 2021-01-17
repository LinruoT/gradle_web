package bitter.service;

public class EncoreableImpl implements Encoreable{
    @Override
    public void sayEncore(String location) {
        System.out.println("encore: "+location);
    }
}
