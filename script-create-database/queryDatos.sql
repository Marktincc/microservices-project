INSERT INTO customersDB.customers (apellidos, contraseña_hash, correo, direccion, nombre, rol, telefono) VALUES
                                                                                                             ('Gómez Pérez', 'hash123', 'gomez@example.com', 'Calle 123, Bogotá', 'Juan', 'admin', '3001234567'),
                                                                                                             ('Martínez López', 'hash456', 'martinez@example.com', 'Carrera 45, Medellín', 'María', 'usuario', '3017654321'),
                                                                                                             ('Rodríguez Sánchez', 'hash789', 'rodriguez@example.com', 'Avenida 78, Cali', 'Carlos', 'editor', '3208765432');
INSERT INTO categoriesDB.categorias (name) VALUES
                                               ('Electrónica'),
                                               ('Ropa'),
                                               ('Alimentos'),
                                               ('Juguetes'),
                                               ('Muebles');
INSERT INTO productosDB.productos (cantidad, categoria_id, nombre_producto, proveedor_id_proveedor, valor) VALUES
                                                                                                               (50, 1, 'Laptop', 2, 2500000.00),
                                                                                                               (30, 2, 'Camisa de algodón', 3, 80000.00),
                                                                                                               (100, 3, 'Arroz 5kg', 1, 20000.00),
                                                                                                               (20, 4, 'Muñeca Barbie', 4, 120000.00),
                                                                                                               (10, 5, 'Escritorio de madera', 5, 450000.00);
