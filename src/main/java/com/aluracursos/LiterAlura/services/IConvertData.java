package com.aluracursos.LiterAlura.services;

public interface IConvertData {
    <T> T obtainData(String json, Class<T> clas);
}
