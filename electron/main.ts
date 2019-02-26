import { app, BrowserWindow } from "electron";
import  * as path from "path";
import { ipcMain } from "electron";

let mainWindow: BrowserWindow;

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

app.on("ready", createWindow);

app.on("window-all-closed", () => {
    if(process.platform !== "darwin") {
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
