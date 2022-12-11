package a2_1901040159;

public class ElectiveModule extends Module {
    private String departmentName;

    public ElectiveModule(String name, int semester, int credits, String departmentName) {
        super(name, semester, credits);
        this.departmentName = departmentName;
    }

    @Override
    public String getDepartName() {
        return departmentName;
    }

    @Override
    public String getType() {
        return "Elective";
    }
}
