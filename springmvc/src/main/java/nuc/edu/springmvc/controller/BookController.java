package nuc.edu.springmvc.controller;

import cn.hutool.core.date.DateUtil;
import nuc.edu.springmvc.entity.Books;
import nuc.edu.springmvc.util.Res;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.*;

/**
 * 中北大学软件学院王袭明版权声明(c) 2022/10/16
 */
@Controller
public class BookController {

    static List<Books> booksList =new ArrayList<Books>();

    static {
        Books books1 =new Books();
        books1.setId(1);
        books1.setName("三国演义");
        books1.setPrice(45.5);
        Books books2 =new Books();
        books2.setId(2);
        books2.setName("水浒传");
        books2.setPrice(45.5);
        Books books3 = new Books();
        books3.setId(3);
        books3.setName("西游记");
        books3.setPrice(45.6);
        booksList.add(books1);
        booksList.add(books2);
        booksList.add(books3);

    }

//    @RequestMapping(value = "/table", method = RequestMethod.GET)
//    public ModelAndView query(){
//        System.out.println("测试访问");
//        Books books1 =new Books();
//        books1.setId(1);
//        books1.setName("phone");
//        Books books2 =new Books();
//        books2.setId(2);
//        books2.setName("computer");
//        booksList.add(books1);
//        booksList.add(books2);
//        ModelAndView modelAndView =new ModelAndView();
//        modelAndView.addObject("userList", booksList);
//        modelAndView.setViewName("table");
//        return modelAndView;
//    }

    /**
     * 查询所有图书
     * @param model
     * @return
     */
    @RequestMapping("/searchall")
    public String query(Model model){
        System.out.println("测试访问");

        model.addAttribute("booksList", booksList);
        return "table";
    }

    /**
     * 按id查询图书
     * @param bookId
     * @param model
     * @return
     */
    @RequestMapping("/search/book")
    public String querylist(Integer bookId, Model model){
        System.out.println("id值为:"+bookId);
        List<Books> list=new ArrayList<Books>();
        for (Books books :booksList
        ) {
            if(books.getId()==bookId){
                list.add(books);
            }
        }
        model.addAttribute("booksList",list);
        return "table";
    }


    /**
     * 添加图书
     * @param book
     * @param model
     * @return
     */
    @RequestMapping("/add/book")
    public String addBook(Books book, Model model){
        book.setId(booksList.size()+1);

        booksList.add(book);
        model.addAttribute("booksList",booksList);
        return "table";
    }

    /*无注解获取参数
    * */
    @RequestMapping("/list")
    @ResponseBody
    public List<Books> querylist(String name){
        List<Books> booksList =new ArrayList<Books>();
        List<Books> list=new ArrayList<Books>();
        Books books1 =new Books();
        books1.setId(1);
        books1.setName("phone");
        Books books2 =new Books();
        books2.setId(2);
        books2.setName("computer");
        list.add(books1);
        list.add(books2);
        for (Books books :list
             ) {
            if(books.getName().equals(name)){
                booksList.add(books);
            }
        }
        return booksList;
    }

    /*注解获取参数
    默认value属性
    required参数是否是必选项
     * */
    @RequestMapping(value = "/query1")
    @ResponseBody
    public Books query1(@RequestParam(value = "id") Integer id, @RequestParam("sname") String name){
        Books books =new Books();
        books.setId(id);
        books.setName(name);
        return books;
    }
    /*
    * 传递数组
    * */
    @GetMapping("/arr")
    @ResponseBody
    public Map<String ,Object> getarr(Integer[] num,String[] str){
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("numarr",num);
        paramMap.put("strarr",str);
        return paramMap;
    }



    /*
    * json传递数据
    * */
    @RequestMapping("/insert")
    @ResponseBody
    public Books insert(@RequestBody Books books){
        System.out.println(books);
        return books;
    }





    @RequestMapping("/pojo")
    public String validatepage(){
        return "pojo";
    }

    @RequestMapping("/validate")
    @ResponseBody
    public  Map<String,Object> validate(@Valid @RequestBody Books books, Errors errors){
        Map<String,Object> errmap=new HashMap<>();
        List<ObjectError> oes=errors.getAllErrors();
        for (ObjectError err: oes
             ) {
            String key=null;
            String msg=null;
            if(err instanceof FieldError){
                FieldError fe=(FieldError) err;
                key=fe.getField(); //获取错误验证字段名
            }else{
                key=err.getObjectName();//获取验证字段名称
            }
            //获取错误消息
            msg=err.getDefaultMessage();
            errmap.put(key,msg);
        }
        return errmap;
    }


    /*
     * RESTFUL风格
     * */
    @GetMapping("/rest/{sid}")
    @ResponseBody
    public Integer rest(@PathVariable Integer sid){
        return sid;
    }
    @GetMapping("/rest/{sid}/{sname}")
    @ResponseBody
    public Map<String,Object> multiparam(@PathVariable Integer sid, @PathVariable String sname){
        Map<String,Object> map=new HashMap<>();
        map.put("学号",sid);
        map.put("姓名",sname);
        return map;
    }
    @PutMapping("/user/{id}")
    @ResponseBody
    public Books updateUser(@PathVariable("id") Integer id){
        Books books =new Books();
        books.setId(id);
        books.setName("hongjun");
        return books;
    }
    @PatchMapping("/user/{id}/{name}")
    @ResponseBody
    public Books changeUser(@PathVariable("id") Integer id, @PathVariable("name") String name){
        Books books =new Books();
        books.setId(id);
        books.setName(name);
        return books;
    }
    @DeleteMapping("/user/{id}")
    @ResponseBody
    public List<Books> delete(@PathVariable("id") Integer id){
        List<Books> booksList =new ArrayList<Books>();
        Books books1 =new Books();
        books1.setId(1);
        books1.setName("phone");
        Books books2 =new Books();
        books2.setId(2);
        books2.setName("computer");
        booksList.add(books1);
        booksList.add(books2);
        for (int i = 0; i< booksList.size(); i++){
            if(booksList.get(i).getId()==id){
                booksList.remove(i);
            }
        }
       return booksList;
    }

    @RequestMapping("/testUpLoad")
    @ResponseBody
    public Res testUpLoad(HttpServletRequest req, @RequestParam("file") MultipartFile file, Model m) {
        try {
            String fileName = System.currentTimeMillis() + file.getOriginalFilename();
            String destFileName = req.getServletContext().getRealPath("") + "uploaded" + File.separator + fileName;
            System.out.println(destFileName);
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs();
            file.transferTo(destFile);
        } catch (Exception e) {
            System.out.println("文件没有找到啊！！");
            return Res.error();
        }
        return Res.ok();
    }
    @RequestMapping("/multiuploadPage")
    public String multiuploadPage() {

        return "multiupload";

    }


    @PostMapping("/multitestUpLoad")
    @ResponseBody
    public Res multitestUpLoad(HttpServletRequest req,@RequestParam("filename")  MultipartFile[] filename) {
        String uploadDir = req.getServletContext().getRealPath("") + "uploaded/";
        System.out.println(uploadDir);
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            for (MultipartFile multiFile : filename) {
                if(!multiFile.isEmpty()){
                executeUpload(uploadDir, multiFile);}
            }

        } catch (Exception e) {
           e.printStackTrace();
            return Res.error();
        }

        return Res.ok();
    }
    private void executeUpload(String uploadDir, MultipartFile file) throws Exception {
        System.out.println("执行文件上传");
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String filename = UUID.randomUUID() + suffix;
        File serverFile = new File(uploadDir + filename);
        file.transferTo(serverFile);
    }


    @RequestMapping("/todate")
    public String toDate(){
        return "format";
    }

    /*
     * 数据格式化
     * */

    @RequestMapping("/date")
    @ResponseBody
    public String printDate(String dateStr) {
        //String dateStr = "2020/10/01";
        Date date=DateUtil.parseDate(dateStr);
        //结果 ****/**/**
        String format = DateUtil.format(date, "yyyy/MM/dd");
        return format;
       //常用格式的格式化，结果：2017-03-01
      /* String formatDate = DateUtil.formatDate(date);
       return formatDate;*/
        //结果：2017-03-01 00:00:00
        /*String formatDateTime = DateUtil.formatDateTime(date);
        return formatDateTime;*/
        //结果：00:00:00
       /* String formatTime = DateUtil.formatTime(date);
        return formatTime;*/
    }

    @GetMapping("/exception")
    public void exceptionpost(){
       // Integer res1= Integer.valueOf("abc");
       // int res2=10/0;
        //throw  new ZdException(201,"用户自定义异常");
    }
}

