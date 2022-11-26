package net.mision_thi.thilib.model_data;

public class User {

    public static class Test {
        private String name;


        Test(String name, String email, String roles, boolean admin) {
            this.name = name;

        }

        public String getName() {
            return name;
        }

    }

    // getters and setters, toString() .... (omitted for brevity)
}
