package guru.springframework.controllers;

import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jt on 1/20/16.
 */
//@Controller
@RestController
public class IndexController {

    private ProductService productService;
    @Autowired
    private Environment environment;

    @Autowired
    public IndexController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping({"/", "index"})
    public String getIndex(){
        String url = environment.getProperty("my.database.url");
        String user = environment.getProperty("my.database.user");
        String password = environment.getProperty("my.database.password");
        return "url="+url+"; user="+user+"; password="+password;
    }

}
