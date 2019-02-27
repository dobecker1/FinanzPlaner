"use strict";
exports.__esModule = true;
var electron_1 = require("electron");
var path = require("path");
var electron_2 = require("electron");
var mainWindow;
var serverProcess;
function createWindow() {
    mainWindow = new electron_1.BrowserWindow({
        height: 800,
        width: 1000
    });
    mainWindow.loadFile(path.join(__dirname, "dist/index.html"));
    mainWindow.webContents.openDevTools();
    mainWindow.on("closed", function () {
        mainWindow = null;
    });
}
electron_1.app.on("ready", function () {
    createWindow();
    //serverProcess = child.spawn("java", ["-jar", "C:/Users/dobe/Documents/Projects/FinanzPlaner/server/build/libs/gs-rest-service-0.1.0.jar"]);
});
electron_1.app.on("window-all-closed", function () {
    if (process.platform !== "darwin") {
        //  serverProcess.kill();
        electron_1.app.quit();
    }
});
electron_1.app.on("activate", function () {
    if (mainWindow === null) {
        createWindow();
    }
});
electron_2.ipcMain.on('onUploadFile', function (event, file) {
    console.log(file);
});
