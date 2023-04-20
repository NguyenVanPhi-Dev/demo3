package com.example.demo3.controller;

import com.example.demo3.entity.Dish;
import com.example.demo3.excel.WorkingExcel;
import com.example.demo3.service.DishService;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("")
public class HomeController {
    @Autowired private DishService dishService;
    @GetMapping("")
    public String index(Model model){
        List<Dish> list = dishService.getAllDish();
        model.addAttribute("dishs", list);
        return "index";
    }
    @GetMapping("/edit/{id}")
        public String edit(@PathVariable("id") Long id, Model model){
            Dish list = dishService.findDishById(id).get();
            model.addAttribute("dish", list);
            return "edit";
        }
    @GetMapping("add")
    public String add(Model model){
        model.addAttribute("dish", new Dish());
        return "add";
    }
    @GetMapping("delete")
    public String delete(@RequestParam("id") Long id){
        dishService.deleteDish(id);
        return "redirect:/";
    }
    @PostMapping(value = "save")
    public String save(@ModelAttribute Dish dish) {
        System.out.println(dish.toString());
        dishService.saveDish(dish);
        return "redirect:/";
    }
    @GetMapping("upload-excel")
    public String readExcel(){
        return "readExcel";
    }
    @PostMapping("upload-excel")
    public String read(@RequestParam("excelFile") MultipartFile file, Model model) throws IOException {
        WorkingExcel workingExcel = new WorkingExcel();
        InputStream inputStream = file.getInputStream();
        List<Dish> dishes =   workingExcel.Reading(inputStream);
        model.addAttribute("dishs", dishes);
        return "index";
    }
    @GetMapping("/download")
    public void downloadFile(HttpServletResponse response) throws IOException {
        String fileName = "example.xlsx";
        String filePath = "src/main/resources/static/example.xlsx";
        WorkingExcel workingExcel = new WorkingExcel();
        workingExcel.Writing(dishService.getAllDish(),filePath);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        InputStream inputStream = new FileInputStream(new File(filePath));
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }

}
