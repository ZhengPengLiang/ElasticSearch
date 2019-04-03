package com.springbootlearn.damo.service;

import com.springbootlearn.damo.entity.Doc;

import java.io.File;

public interface ITikaService {
     Doc parserExtraction(File file);
}
