import io.javalin.Javalin;

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
        app.get("/", ctx -> ctx.result("Hello World!"));
    }
}
