package bitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillyServiceImpl implements BillyService {
    private String fullName;
    private String location;

    @Override
    public String getFullName() {
        return fullName;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Autowired
    public BillyServiceImpl(@Value("${billy.fullName}") String fullName, @Value("${billy.location}") String location) {
        System.out.println("@Value billy.fullName "+fullName);
        System.out.println("@Value billy.location "+location);
        this.fullName = fullName;
        this.location = location;
    }
}
