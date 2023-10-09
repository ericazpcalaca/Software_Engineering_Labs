package javat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;
@RequestMapping
@Controller
public class CategoryController {

    @Autowired
    Catdao dao;

    @GetMapping(path="/category")
    public String showCategorypage(ModelMap model) throws ClassNotFoundException, SQLException{
        List<Category> list = dao.display();

        model.addAttribute("categorylist", list);
        model.put("id", list.get(0).getCatcode());
        model.put("desc", list.get(0).getCatdesc());
        return "category";
    }

    @GetMapping(path="/")
    public String showCategorypage2(ModelMap model) throws ClassNotFoundException, SQLException{
        List<Category> list = dao.display();

        model.addAttribute("categorylist", list);
        model.put("id", list.get(0).getCatcode());
        model.put("desc", list.get(0).getCatdesc());
        return "category";
    }
}
