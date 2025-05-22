use productsdb

DELIMITER $$

CREATE PROCEDURE get_product_info()
BEGIN

    SELECT
        p.id AS id,
        p.nombre_producto AS nombreProducto,
        p.cantidad,
        p.valor,
        IFNULL(c.name, 'Sin categoría') AS categoriaNombre,
        IFNULL(s.nombre, 'Sin proveedor') AS proveedorNombre
    FROM
        productos p
            LEFT JOIN
        categoriesdb.categorias c ON p.categoria_id = c.id
            LEFT JOIN
        suppliersdb.proveedores s ON p.proveedor_id_proveedor = s.id;
END $$



USE salesDb;
DELIMITER $$

CREATE PROCEDURE `get_sale_info`()
BEGIN
    SELECT
        s.id AS id_venta,
        s.fecha AS fecha_venta,
        s.cantidad AS cantidad,
        s.valor AS valor_total,

        -- Info producto
        p.nombre_producto AS productoNombre,


        -- Info cliente
        c.nombre AS clienteNombre


    FROM ventas s
             LEFT JOIN productsdb.productos p ON s.id_producto = p.id
             LEFT JOIN customersdb.customers c ON s.id_usuario = c.id;
END$$

DELIMITER ;

CALL get_sale_info();


