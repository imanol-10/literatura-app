package com.alura.literatura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosLibro{
        private String title;
        private List<DatosAutor> authors;
        private List<String> languages;
        private Integer download_count;

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public List<DatosAutor> getAuthors() {
                return authors;
        }

        public void setAuthors(List<DatosAutor> authors) {
                this.authors = authors;
        }

        public List<String> getLanguages() {
                return languages;
        }

        public void setLanguages(List<String> languages) {
                this.languages = languages;
        }

        public Integer getDownload_count() {
                return download_count;
        }

        public void setDownload_count(Integer download_count) {
                this.download_count = download_count;
        }
}
