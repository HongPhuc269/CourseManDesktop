package a2_1901040159;

public class CompulsoryModule extends Module {
    public CompulsoryModule(String name, int semester, int credits) {
        super(name, semester, credits);
    }

    @Override
    public String getType() {
        return "Compulsory";
    }
}
