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

DELIMITER ;
