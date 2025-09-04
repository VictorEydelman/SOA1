window.onload = function() {
  //<editor-fold desc="Changeable Configuration Block">

  // Первый Swagger UI
  window.ui = SwaggerUIBundle({
    url: "../Service1.yaml",
    dom_id: '#swagger-ui',
    deepLinking: true,
    presets: [
      SwaggerUIBundle.presets.apis,
      SwaggerUIStandalonePreset
    ],
    plugins: [
      SwaggerUIBundle.plugins.DownloadUrl
    ],
    layout: "StandaloneLayout"
  });

  // Второй Swagger UI
  window.ui2 = SwaggerUIBundle({
    url: "../Service2.yaml", // Укажите путь к вашему второму YAML файлу
    dom_id: '#swagger-ui-2', // Уникальный идентификатор для второго контейнера
    deepLinking: true,
    presets: [
      SwaggerUIBundle.presets.apis,
      SwaggerUIStandalonePreset
    ],
    plugins: [
      SwaggerUIBundle.plugins.DownloadUrl
    ],
    layout: "StandaloneLayout"
  });

  //</editor-fold>
};
