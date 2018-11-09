package com.springbootlearn.damo.service;

import com.springbootlearn.damo.Entity.Doc;

import java.io.File;

public interface ITikaService {
     Doc parserExtraction(File file);
}
