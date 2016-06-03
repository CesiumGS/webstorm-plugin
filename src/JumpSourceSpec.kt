import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx
import com.intellij.openapi.vfs.LocalFileSystem
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
            var newFilePath = oldFilePath.replace("Source", "Specs")
            newFilePath = newFilePath.substring(7, newFilePath.length - 3) + "Spec" + newFilePath.substring(newFilePath.length - 3, newFilePath.length)
            val newVirtualFile = LocalFileSystem.getInstance().findFileByIoFile(File(newFilePath)) ?: return
            fileEditorManager.closeFile(oldVirtualFile)
            fileEditorManager.openFile(newVirtualFile, true)
        } else if (oldFilePath.contains("Specs")) {
            var newFilePath = oldFilePath.replace("Specs", "Source")
            newFilePath = newFilePath.substring(7, newFilePath.length - 7) + "" + newFilePath.substring(newFilePath.length - 3, newFilePath.length)
            val newVirtualFile = LocalFileSystem.getInstance().findFileByIoFile(File(newFilePath)) ?: return
            fileEditorManager.closeFile(oldVirtualFile)
            fileEditorManager.openFile(newVirtualFile, true)
        }
    }
}
