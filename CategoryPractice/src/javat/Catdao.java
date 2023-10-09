package javat;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.tree.RowMapper;
import java.sql.SQLException;
import java.util.List;

//@Autowired
@Component
@Service
public class Catdao {

    JdbcTemplate template;
    public Catdao(JdbcTemplate template){
        this.template = template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public List<Category> display() throws ClassNotFoundException, SQLException{
        return template.query("select * from category", (rs,row) -> {
            
            //Create an array list that will contain the data recovered
            Category c = new Category();
            c.setCatcode(rs.getString(1));
            c.setCatdesc(rs.getString(2));
            return c;
        });
    }
}
