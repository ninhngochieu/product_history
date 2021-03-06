package DAL;

import DTO.ProductDTO;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductDAL extends DB implements DAL {

    public int getPriceById(String id) {
        try {
            String sql = "SELECT * FROM `history` WHERE id_product = "+id+" ORDER BY id DESC LIMIT 1";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            return rs.getInt("current_price");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean insert(Object o) {
        ProductDTO productTiki = (ProductDTO) o;
        try {
            String sql = "INSERT INTO `product`(`id`, `name`, `image`, `price`, `id_item`) VALUES (?,?,?,?,?)";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1,productTiki.getId());
            statement.setString(2,productTiki.getName());
            statement.setString(3,productTiki.getImage());
            statement.setInt(4,productTiki.getPrice());
            statement.setInt(5,productTiki.getReview_count());
            statement.setString(6,productTiki.getId_item());
            return statement.executeUpdate()!=0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Object o) {
        ProductDTO x = (ProductDTO) o;
        try {
            String sql = "UPDATE `product` SET `name`=?,`image`=?,`price`=?,`review_count`=?,`id_item`=?,`rating_average`=?,`star`=? WHERE `id` = ? ";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1,x.getName());
            statement.setString(2,x.getImage());
            statement.setInt(3,x.getPrice());
            statement.setInt(4,x.getReview_count());
            statement.setString(5,x.getId_item());
            statement.setFloat(6,x.getRating_average());
            statement.setObject(7,x.getStar().toString());
            statement.setString(8,x.getId());
            return statement.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public boolean found(Object o) {
        ProductDTO p = (ProductDTO) o;
        try {
            String sql = "SELECT 1 FROM `product` WHERE id = "+p.getId();
            Statement statement = this.connection.createStatement();
            return statement.executeQuery(sql).first();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Object> getAll() {
        ArrayList<Object> productDTOS = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `product`";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                ProductDTO product = new ProductDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getString("id_item"),
                        rs.getInt("price"),
                        rs.getInt("review_count"),
                        rs.getFloat("rating_average"),
                        new JSONObject(rs.getString("star"))
                );
                productDTOS.add(product);
            }
         } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productDTOS;
    }

    public ArrayList<Object> getByName(String name, int per_page, int current_page) {
        ArrayList<Object> productDTOS = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `product` WHERE `name` LIKE '%"+name+"%' LIMIT "+per_page+" OFFSET "+(current_page-1)*per_page+"";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                ProductDTO product = new ProductDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getString("id_item"),
                        rs.getInt("price"),
                        rs.getInt("review_count"),
                        rs.getFloat("rating_average"),
                        new JSONObject(rs.getString("star"))
                );
                productDTOS.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productDTOS;
    }

    public ArrayList<Object> getByIdName(String id_item, String name, int per_page, int current_page) {
        ArrayList<Object> productDTOS = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `product` WHERE `id_item` = '"+id_item+"' AND `name` LIKE '%"+name+"%' LIMIT "+per_page+" OFFSET "+(current_page-1)*per_page+"";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                ProductDTO product = new ProductDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getString("id_item"),
                        rs.getInt("price"),
                        rs.getInt("review_count"),
                        rs.getFloat("rating_average"),
                        new JSONObject(rs.getString("star"))
                );
                productDTOS.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productDTOS;
    }

    public ArrayList<Object> getTotalByName(String name) {
        ArrayList<Object> productDTOS = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `product` WHERE `name` LIKE '%"+name+"%'";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                ProductDTO product = new ProductDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getString("id_item"),
                        rs.getInt("price"),
                        rs.getInt("review_count"),
                        rs.getFloat("rating_average"),
                        new JSONObject(rs.getString("star"))
                );
                productDTOS.add(product);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(productDTOS.toString());
        return productDTOS;
    }

    public ArrayList<ProductDTO> getByName(String name) {
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `product` WHERE MATCH (`name`) AGAINST ('"+name+"' IN NATURAL LANGUAGE MODE)";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                ProductDTO product = new ProductDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getString("id_item"),
                        rs.getInt("price"),
                        rs.getInt("review_count"),
                        rs.getFloat("rating_average"),
                        new JSONObject(rs.getString("star"))
                );
                productDTOS.add(product);
            }
        } catch (SQLException throwables) {

        }
        return productDTOS;
    }
    public ArrayList<ProductDTO> getByNameTest(String name) {
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `product` WHERE MATCH (`name`) AGAINST ('?' IN NATURAL LANGUAGE MODE)";
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setString(1,name);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                ProductDTO product = new ProductDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getString("id_item"),
                        rs.getInt("price"),
                        rs.getInt("review_count"),
                        rs.getFloat("rating_average"),
                        new JSONObject(rs.getString("star"))
                );
                productDTOS.add(product);
            }
        } catch (SQLException throwables) {

        }
        return productDTOS;
    }

    public ArrayList<ProductDTO> getByAllName(String name) {
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `product` WHERE `name` LIKE '%"+name+"%'";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                ProductDTO product = new ProductDTO(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getString("id_item"),
                        rs.getInt("price"),
                        rs.getInt("review_count"),
                        rs.getFloat("rating_average"),
                        new JSONObject(rs.getString("star"))
                );
                productDTOS.add(product);
            }
            return productDTOS;
        } catch (SQLException throwables) {

        }
        return productDTOS;
    }

    public ArrayList<String> suggestProductByName(String key) {
        ArrayList<String> productDTOS = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `product` WHERE MATCH (`name`) AGAINST ('"+key+"' IN NATURAL LANGUAGE MODE) LIMIT 6";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                productDTOS.add(rs.getString("name"));
            }
        } catch (SQLException throwables) {

        }
        return productDTOS;
    }

    public ArrayList<String> suggestAllProductByName(String key) {
        ArrayList<String> productDTOS = new ArrayList<>();
        try {
            String sql = "SELECT `name` FROM `product` WHERE `name` LIKE '"+key+"%' LIMIT 6";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                productDTOS.add(rs.getString("name"));
            }
        } catch (SQLException throwables) {

        }
        return productDTOS;
    }

    public ArrayList<String> getAllNameProduct() {
        ArrayList<String> productDTOS = new ArrayList<>();
        try {
            String sql = "SELECT `name` FROM `product`";
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                String s = rs.getString("name");
                String chuan_hoa = s.replace("- Hàng Nhập Khẩu","");
                chuan_hoa = chuan_hoa.replace("- Hàng Chính Hãng","");
                chuan_hoa = chuan_hoa.replace("- Hàng chính hãng","");
                chuan_hoa = chuan_hoa.replace("- Màu Ngẫu Nhiên","");
                chuan_hoa = chuan_hoa.replace("- Màu Đen","");
                productDTOS.add(chuan_hoa);
            }
        } catch (SQLException throwables) {

        }
        return productDTOS;
    }
}
