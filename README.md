# Dragolandia

## Introducción del proyecto
Dragolandia es una aplicación de simulación de combate medieval desarrollada en Java, que utiliza Hibernate como framework de persistencia de datos (ORM) y MySQL como sistema de gestión de bases de datos. El proyecto ha sido diseñado siguiendo el patrón de diseño MVC (Modelo-Vista-Controlador).

## Análisis

### Diagrama de clases
[![](https://mermaid.ink/img/pako:eNqtVd1u2jAUfhXLV2yliKQ0gajqRX-2aQJRtVxNuTnEh-AtsZmTVB0I3mnPsBebE2xI0q6qqkWC-JzznePP58fZ0EgypAGNEsiyGw6xgjQURD-MK4xyLgWZXYVir6tQZAKxJJu9pnxOucgJZzXFQ664iImQ6VxhC_jIGbRUgj9ioqPyumHMs3z3BaMlX8sdiaT4XiiZHQEnCYg1KIPoTKTIclVIkprFh7dDu8SYyHL_Nr7b1rmt3_86-4yv5CForoUWfFGgWtccTiCHCFSnKkCq_17meaO0STRYvkZK_1Bkmhn7VGDc5qAw04VAEdWLc4JPS0hKJv_IeovRlcx-Fvj-vFUNcocJj1Wd37Pdv-IC2y1kQTuSaBGsWO-kVGoVqNK7U2-bCNI5N_pX-wsYs_a3psR2XC0nFxcwL4lE-eXlezPF4M9vWWe2SrhumduFnuU3c2v0ZYMgiiJFBeW1UOc4_Xw_PUqz--l4fBRvH-5ur2cWcNiqauKQfgwpOT01C5uUoDXvpn9C6tTRB4rBi6V97uQ0nZ53jZmclosJFOjxfUSClr8NpHGNhAVmlGvH7PUaZMvbyF41BFrhSmzpE5Bq2q3dUGtG2o-hhdjsNTFQll5jaJfGijMaaD12qS5jCqVIqwKHNF9iiiEN9JKB-hHSUGy1zwrENylT66ZkES9psIAk01KxYpCj-WwctAoFQ3UtC5HTwPeGVRAabOgTDZyR13MH3rk38s8Grtd3z7v0l1b3e_2-5_ijoXfuj9wz39t26bra1-m5I3_gD8-coesMBo6nPZDxXKqJ-XSVr-1f4x0WFw?type=png)](https://mermaid.live/edit#pako:eNqtVd1u2jAUfhXLV2yliKQ0gajqRX-2aQJRtVxNuTnEh-AtsZmTVB0I3mnPsBebE2xI0q6qqkWC-JzznePP58fZ0EgypAGNEsiyGw6xgjQURD-MK4xyLgWZXYVir6tQZAKxJJu9pnxOucgJZzXFQ664iImQ6VxhC_jIGbRUgj9ioqPyumHMs3z3BaMlX8sdiaT4XiiZHQEnCYg1KIPoTKTIclVIkprFh7dDu8SYyHL_Nr7b1rmt3_86-4yv5CForoUWfFGgWtccTiCHCFSnKkCq_17meaO0STRYvkZK_1Bkmhn7VGDc5qAw04VAEdWLc4JPS0hKJv_IeovRlcx-Fvj-vFUNcocJj1Wd37Pdv-IC2y1kQTuSaBGsWO-kVGoVqNK7U2-bCNI5N_pX-wsYs_a3psR2XC0nFxcwL4lE-eXlezPF4M9vWWe2SrhumduFnuU3c2v0ZYMgiiJFBeW1UOc4_Xw_PUqz--l4fBRvH-5ur2cWcNiqauKQfgwpOT01C5uUoDXvpn9C6tTRB4rBi6V97uQ0nZ53jZmclosJFOjxfUSClr8NpHGNhAVmlGvH7PUaZMvbyF41BFrhSmzpE5Bq2q3dUGtG2o-hhdjsNTFQll5jaJfGijMaaD12qS5jCqVIqwKHNF9iiiEN9JKB-hHSUGy1zwrENylT66ZkES9psIAk01KxYpCj-WwctAoFQ3UtC5HTwPeGVRAabOgTDZyR13MH3rk38s8Grtd3z7v0l1b3e_2-5_ijoXfuj9wz39t26bra1-m5I3_gD8-coesMBo6nPZDxXKqJ-XSVr-1f4x0WFw)

## Diseño

### Diagrama E-R

![Esquema Entidad Relación](/img/EntidadRelacion.png)

## Ampliación y posibles mejoras

1. Actualmente el mago lanza hechizos infinitos (si los conoce). Añadir un atributo maná que se consuma según el poder del hechizo.

2. Implementar un sistema de EXP. Al derrotar monstruos, el mago sube de nivel, lo que aumenta su vida y nivelMagia.

3. Añadir un factor de azar a los ataques para que exista la probabilidad de ataques "críticos".

## Manual de usuario

[Enlace a manual de usuario](ManualDeUsuario.md)

#### Apuntes sobre mi trabajo

Faltan cosas por implementar como las operaciones CRUD de borrar y actualizar, la parte de hechizos y alguna cosa más. Intenté dejarlo
lo más completo posible para cumplir con los requisitos y poder ver los fallos tras la corrección. La batalla es muy simple porque no pude 
implementarla al completo. Soy consciente de todo el tiempo que tuvimos y que podría haberlo hecho bastante mejor.