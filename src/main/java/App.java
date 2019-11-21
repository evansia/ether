import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.crud;

public class App {
    public static void main(String[] args) {
//        DB db = new DB();
//        List<Upload> allUploads = db.fetchAllUploads();
//        for (Upload upload : allUploads) {
//            System.out.println(upload.getId() + ": " + upload.getName());
//        }
//
////        db.insert();
        Javalin app = Javalin.create().start(5100);
        DB db = new DB();
        //app.get("/", ctx -> ctx.result("Hello World!"));
        app.routes(() -> {
            crud("uploads/:id", new UploadController());
        });
    }
}
