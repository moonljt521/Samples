package com.example.annotation.seriable;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * author: moon
 * created on: 17/8/15 下午2:37
 * description:
 */
//@AutoService(Processor.class)
@SupportedAnnotationTypes("com.example.annotation.seriable.Seriable")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class BeanProcessor extends AbstractProcessor { // 元素操作的辅助类
    Elements elementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        // 元素操作的辅助类
        elementUtils = processingEnv.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // 获得被该注解声明的元素
        Set<? extends Element> elememts = roundEnv
                .getElementsAnnotatedWith(Seriable.class);
        TypeElement classElement = null;// 声明类元素
        List<VariableElement> fields = null;// 声明一个存放成员变量的列表
        // 存放二者
        Map<String, List<VariableElement>> maps = new HashMap<String, List<VariableElement>>();
        // 遍历
        for (Element ele : elememts) {
            if (ele.getKind() == ElementKind.CLASS) {
                // 判断该元素是否为类

                classElement = (TypeElement) ele;
                maps.put(classElement.getQualifiedName().toString(),
                        fields = new ArrayList<VariableElement>());

            } else if (ele.getKind() == ElementKind.FIELD) {
                // 判断该元素是否为成员变量

                VariableElement varELe = (VariableElement) ele;
                // 获取该元素封装类型
                TypeElement enclosingElement = (TypeElement) varELe
                        .getEnclosingElement();
                // 拿到key
                String key = enclosingElement.getQualifiedName().toString();
                fields = maps.get(key);
                if (fields == null) {
                    maps.put(key, fields = new ArrayList<VariableElement>());
                }
                fields.add(varELe);
            }
        }

        for (String key : maps.keySet()) {
            if (maps.get(key).size() == 0) {
                TypeElement typeElement = elementUtils.getTypeElement(key);
                List<? extends Element> allMembers = elementUtils
                        .getAllMembers(typeElement);
                if (allMembers.size() > 0) {
                    maps.get(key).addAll(ElementFilter.fieldsIn(allMembers));
                }
            }
        }
        Messager messager = processingEnv.getMessager();

        messager.printMessage(Diagnostic.Kind.NOTE, "start。。。。。。");
        System.out.println("map size = " + maps.size());
        messager.printMessage(Diagnostic.Kind.NOTE, "打印map 信息:" + maps.toString());
        messager.printMessage(Diagnostic.Kind.NOTE, "end......");

        // test 生成一个java 文件
        testGenerateJavaFile();

        // test 生成一个本地文件
        generateCodes(maps);

        return true;
    }

    private void testGenerateJavaFile() {

        JavaFileObject source = null;
        try {
            source = processingEnv.getFiler().createSourceFile("com.example.annotation.seriable.GeneratedClass");


            StringBuilder builder = new StringBuilder()
                    .append("package com.example.annotation.seriable;\n\n")
                    .append("public class GeneratedClass {\n\n") // open class
                    .append("\tpublic String getValue() {\n") // open method
                    .append("\t\treturn \"原木");

            builder.append("\";\n") // end return
                    .append("\t}\n") // close method
                    .append("}\n"); // close class



            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateCodes(Map<String, List<VariableElement>> maps) {
        File dir = new File("/Users/moon/Documents/test");
        if (!dir.exists())
            dir.mkdirs();
        // 遍历map
        for (String key : maps.keySet()) {

            // 创建文件
            File file = new File(dir, key.replaceAll("\\.", "_") + ".txt");
            try {
                /**
                 * 编写json文件内容
                 */
                FileWriter fw = new FileWriter(file);
                fw.append("{").append("class:").append("\"" + key + "\"")
                        .append(",\n ");
                fw.append("fields:\n {\n");
                List<VariableElement> fields = maps.get(key);

                for (int i = 0; i < fields.size(); i++) {
                    VariableElement field = fields.get(i);
                    fw.append("  ").append(field.getSimpleName()).append(":")
                            .append("\"" + field.asType().toString() + "\"");
                    if (i < fields.size() - 1) {
                        fw.append(",");
                        fw.append("\n");
                    }
                }
                fw.append("\n }\n");
                fw.append("}");
                fw.flush();
                fw.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}