"use strict";
exports.__esModule = true;
var electron_1 = require("electron");
var path = require("path");
var electron_2 = require("electron");
var mainWindow;
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
electron_1.app.on("ready", createWindow);
electron_1.app.on("window-all-closed", function () {
    if (process.platform !== "darwin") {
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
