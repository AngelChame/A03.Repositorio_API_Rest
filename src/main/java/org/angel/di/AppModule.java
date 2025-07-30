package org.angel.di;

import org.angel.controller.ProductController;
import org.angel.controller.SaleController;
import org.angel.controller.UserController;
import org.angel.repository.ProductRepository;
import org.angel.repository.SaleRepository;
import org.angel.repository.UserRepository;
import org.angel.routes.ProductsRoutes;
import org.angel.routes.SalesRoutes;
import org.angel.routes.UserRoutes;
import org.angel.service.ProductService;
import org.angel.service.SaleService;
import org.angel.service.UserService;
import org.angel.controller.AlumnoController;
import org.angel.repository.AlumnoRepository;
import org.angel.routes.AlumnoRoutes;
import org.angel.service.AlumnoService;

public class AppModule {
    public static UserRoutes initUser() {
        UserRepository userRepo = new UserRepository();
        UserService userService = new UserService(userRepo);
        UserController userController = new UserController(userService);
        return new UserRoutes(userController);
    }

    public static ProductsRoutes initProducts() {
        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);
        ProductController productController = new ProductController(productService);
        ProductsRoutes productsRoutes = new ProductsRoutes(productController);
        return productsRoutes;
    }

    public static SalesRoutes initSales() {
        SaleRepository saleRepository = new SaleRepository();
        SaleService saleService = new SaleService(saleRepository);
        SaleController saleController = new SaleController(saleService);
        SalesRoutes salesRoutes = new SalesRoutes(saleController);
        return salesRoutes;
    }

    public static AlumnoRoutes initAlumno() {
        AlumnoRepository alumnoRepository = new AlumnoRepository();
        AlumnoService alumnoService = new AlumnoService(alumnoRepository);
        AlumnoController alumnoController = new AlumnoController(alumnoService);
        AlumnoRoutes alumnoRoutes = new AlumnoRoutes(alumnoController);
        return alumnoRoutes;
    }
}
