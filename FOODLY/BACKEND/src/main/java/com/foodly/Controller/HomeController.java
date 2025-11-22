package com.foodly.Controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String home() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        return String.format("""
                <!DOCTYPE html>
                <html lang="pt-BR">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Foodly API</title>
                    <link rel="icon" type="image/png" href="assets/favicon.png">
                    <style>
                        * { margin: 0; padding: 0; box-sizing: border-box; }
                        body {
                            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                            background: #D3D3D3;
                            min-height: 100vh;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                            padding: 20px;
                        }
                        .container {
                            background: white;
                            border-radius: 20px;
                            padding: 40px;
                            box-shadow: 0 20px 60px rgba(0,0,0,0.3);
                            max-width: 600px;
                            width: 100%%;
                            animation: fadeIn 0.5s ease-in;
                        }
                        @keyframes fadeIn {
                            from { opacity: 0; transform: translateY(-20px); }
                            to { opacity: 1; transform: translateY(0); }
                        }
                        .logo {
                            text-align: center;
                            font-size: 48px;
                            margin-bottom: 10px;
                        }
                        h1 {
                            color: #667eea;
                            text-align: center;
                            margin-bottom: 10px;
                            font-size: 32px;
                        }
                        .status {
                            text-align: center;
                            color: #28a745;
                            font-weight: bold;
                            font-size: 18px;
                            margin-bottom: 30px;
                        }
                        .info {
                            background: #f8f9fa;
                            border-radius: 10px;
                            padding: 20px;
                            margin-bottom: 20px;
                        }
                        .info-item {
                            display: flex;
                            justify-content: space-between;
                            padding: 10px 0;
                            border-bottom: 1px solid #dee2e6;
                        }
                        .info-item:last-child { border-bottom: none; }
                        .label { font-weight: 600; color: #495057; }
                        .value { color: #6c757d; }
                        .endpoints {
                            margin-top: 20px;
                        }
                        .endpoint {
                            background: #667eea;
                            color: white;
                            padding: 12px 20px;
                            border-radius: 8px;
                            margin: 10px 0;
                            text-decoration: none;
                            display: block;
                            transition: all 0.3s ease;
                            text-align: center;
                        }
                        .endpoint:hover {
                            background: #1f44e7;
                            transform: translateY(-2px);
                            box-shadow: 0 5px 15px rgba(0,0,0,0.2);
                        }
                        .footer {
                            text-align: center;
                            margin-top: 20px;
                            color: #6c757d;
                            font-size: 14px;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="logo">⚙️</div>
                        <h1>API do Foodly</h1>
                        <div class="status">✓ Funcionando</div>

                        <div class="info">
                            <div class="info-item">
                                <span class="label">Versão:</span>
                                <span class="value">2.0.0</span>
                            </div>
                            <div class="info-item">
                                <span class="label">Status:</span>
                                <span class="value">OK</span>
                            </div>
                            <div class="info-item">
                                <span class="label">Feito com:</span>
                                <span class="value">SpringBoot + MySQL</span>
                            </div>
                            <div class="info-item">
                                <span class="label">Horário da execução:</span>
                                <span class="value">%s</span>
                            </div>
                        </div>

                        <div class="endpoints">
                            <h3 style="color: #495057; margin-bottom: 15px;">Endpoints Disponíveis:</h3>
                            <a href="/api" class="endpoint">⚠️ GET /api - Informações (JSON)</a>
                            <a href="/health" class="endpoint">❗️ GET /health - Checar SQL</a>
                        </div>
                    </div>
                </body>
                </html>
                """, timestamp);
    }

    @GetMapping("/api")
    public ResponseEntity<Map<String, Object>> api() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "A API está rodando!");
        response.put("version", "2.0.0");
        response.put("timestamp", LocalDateTime.now());
        response.put("status", "OK");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("database", "MySQL");
        return ResponseEntity.ok(response);
    }
}
