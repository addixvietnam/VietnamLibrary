package vn.addix.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public abstract class Config {
    public abstract String[] readConfig(String fileName);
    public abstract void writeConfig(String fileName, String content);
    public abstract void deleteConfig(String fileName);
}
