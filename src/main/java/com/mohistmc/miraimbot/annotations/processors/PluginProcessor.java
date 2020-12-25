package com.mohistmc.miraimbot.annotations.processors;

import com.mohistmc.miraimbot.annotations.Plugin;
import com.mohistmc.yaml.file.YamlConfiguration;
import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Names;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

@SupportedAnnotationTypes("com.mohistmc.miraimbot.annotations.Plugin")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class PluginProcessor extends AbstractProcessor {
    private Messager messager;
    private JavacTrees trees;
    private TreeMaker treeMaker;
    private Names names;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        if (System.getProperty("log4j.configurationFile") == null) {
            System.setProperty("log4j.configurationFile", "log4j2.xml");
        }
        System.out.println("Init...");
        super.init(processingEnv);
        this.messager = processingEnv.getMessager();
        this.trees = JavacTrees.instance(processingEnv);
        Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
        this.treeMaker = TreeMaker.instance(context);
        this.names = Names.instance(context);
    }

    @Override
    public synchronized boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> clazz = roundEnv.getElementsAnnotatedWith(Plugin.class);
        if (clazz.size() > 1) {
            try {
                throw new RuntimeException("More than one plugin");
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        clazz.forEach(element -> {
            System.out.println("Processing plugin " + element);
            Plugin plugin = element.getAnnotation(Plugin.class);
            if (plugin == null) {
                try {
                    throw new RuntimeException("Has not annotation at " + element);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Authors: " + Arrays.toString(plugin.authors()));
                System.out.println("Name: " + plugin.value());
                System.out.println("Version: " + plugin.version());
                System.out.println("Description: " + plugin.description());
                Filer filer = processingEnv.getFiler();
                FileObject fileObject = null;
                try {
                    fileObject = filer.getResource(StandardLocation.CLASS_OUTPUT, "", "results");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                File dir = new File(fileObject.toUri());
                if (!dir.exists()) dir.mkdirs();
                File file = new File(dir, "../../../../resources/main/plugin.yml");
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
                yaml.set("main", element);
                yaml.set("author", plugin.authors());
                yaml.set("version", plugin.version());
                yaml.set("description", plugin.description());
                System.out.println("Creating plugin.yml...");
                try {
                    yaml.save(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Success!");
            }

        });
        return true;
    }
}
