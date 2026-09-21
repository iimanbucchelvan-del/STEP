public class Practice_Q3_Bus_Route_Ranking_Engine {
    static class BusRoute {
        private String routeCode, routeName;
        private int priority;

        public BusRoute(String routeCode, String routeName, int priority) {
            this.routeCode = routeCode;
            this.routeName = routeName;
            this.priority = priority;
        }

        public BusRoute(String routeCode, String routeName) {
            this(routeCode, routeName, 3);
        }

        int compareTo(BusRoute other) {
            if (this.priority != other.priority)
                return Integer.compare(other.priority, this.priority);

            int code = this.routeCode.compareToIgnoreCase(other.routeCode);
            if (code != 0)
                return code;

            int name = this.routeName.compareToIgnoreCase(other.routeName);
            if (name != 0)
                return name;

            return 0;
        }
    }

    static BusRoute[] rankRoutes(BusRoute[] routes) {
        BusRoute[] result = routes.clone();

        // Stable bubble sort: equal elements are never swapped.
        for (int i = 0; i < result.length - 1; i++) {
            for (int j = 0; j < result.length - 1 - i; j++) {
                if (result[j].compareTo(result[j + 1]) > 0) {
                    BusRoute temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        BusRoute[] routes = {
            new BusRoute("RT205L", "Airport Express", 3),
            new BusRoute("rt201j", "City Central", 4),
            new BusRoute("RT299T", "Night Service")
        };

        for (BusRoute r : rankRoutes(routes))
            System.out.println(r.routeCode);
    }
}
