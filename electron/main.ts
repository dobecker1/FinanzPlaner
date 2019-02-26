import { app, BrowserWindow } from "electron";
import  * as path from "path";
import { ipcMain } from "electron";
import * as child from "child_process";

let mainWindow: BrowserWindow;
var serverProcess;

function createWindow() {
    mainWindow = new BrowserWindow({
        height: 800,
        width: 1000
    });

    mainWindow.loadFile(path.join(__dirname, "dist/index.html"));

    mainWindow.webContents.openDevTools();

    mainWindow.on("closed", () => {
        mainWindow = null;
    });
}

app.on("ready", () => {
    createWindow();
    serverProcess = child.spawn("java", ["-jar", "C:/Users/dobe/Documents/Projects/FinanzPlaner/server/build/libs/gs-rest-service-0.1.0.jar"]);
});

app.on("window-all-closed", () => {
    if(process.platform !== "darwin") {
        serverProcess.kill();
        app.quit();
    }
});

app.on("activate", () => {
    if(mainWindow === null) {
        createWindow();
    }
});

ipcMain.on('onUploadFile', (event, file) => {
    console.log(file);
});
