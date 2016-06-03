import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import java.io.File

final class JumpSourceSpec : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val fileEditorManager = FileEditorManagerEx.getInstanceEx(project)
        val editor = FileEditorManager.getInstance(project).selectedTextEditor ?: return
        val document = editor.document
        val oldVirtualFile = FileDocumentManager.getInstance().getFile(document) ?: return
        val oldFilePath = oldVirtualFile.url
        if (oldFilePath.contains("Source")) {
            this.sourceToSpec(oldVirtualFile, oldFilePath, "Source", "Specs", fileEditorManager)
        } else if (oldFilePath.contains("lib") && !oldFilePath.contains("Spec")) {
            this.sourceToSpec(oldVirtualFile, oldFilePath, "lib", "specs/lib", fileEditorManager)
        } else if (oldFilePath.contains("Specs")) {
            this.specToSource(oldVirtualFile, oldFilePath, "Specs", "Source", fileEditorManager)
        } else if (oldFilePath.contains("specs/lib")) {
            this.specToSource(oldVirtualFile, oldFilePath, "specs/lib", "lib", fileEditorManager)
        }
    }

    private final fun sourceToSpec(oldVirtualFile: VirtualFile, oldFilePath: String, old: String, new: String, fileEditorManager: FileEditorManagerEx) {
        var newFilePath = oldFilePath.replace(old, new)
        newFilePath = newFilePath.substring(7, newFilePath.length - 3) + "Spec" + newFilePath.substring(newFilePath.length - 3, newFilePath.length)
        val newVirtualFile = LocalFileSystem.getInstance().findFileByIoFile(File(newFilePath)) ?: return
        fileEditorManager.closeFile(oldVirtualFile)
        fileEditorManager.openFile(newVirtualFile, true)
    }

    private final fun specToSource(oldVirtualFile: VirtualFile, oldFilePath: String, old: String, new: String, fileEditorManager: FileEditorManagerEx) {
        var newFilePath = oldFilePath.replace(old, new)
        newFilePath = newFilePath.substring(7, newFilePath.length - 7) + "" + newFilePath.substring(newFilePath.length - 3, newFilePath.length)
        val newVirtualFile = LocalFileSystem.getInstance().findFileByIoFile(File(newFilePath)) ?: return
        fileEditorManager.closeFile(oldVirtualFile)
        fileEditorManager.openFile(newVirtualFile, true)
    }
}
