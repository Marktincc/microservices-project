# Comandos Git Útiles

Este documento contiene una lista de comandos útiles para trabajar con Git en la gestión de versiones y ramas.

## Verificar Actualizaciones

```bash
git pull
```
Este comando permite actualizar el repositorio local con los cambios más recientes del repositorio remoto.

## Comparar Cambios en un Archivo

```bash
git diff <nombre_archivo>
```
Muestra las diferencias entre la versión actual del archivo y la última versión confirmada en Git.

## Ver el Historial de Commits

```bash
git log --oneline
```
Muestra una lista resumida de los commits realizados en el repositorio.

## Restaurar un Archivo a un Commit Específico

```bash
git restore --source <código_commit> <nombre_archivo>
```
Restaura el archivo especificado a una versión específica usando el código del commit.

## Listar Ramas del Repositorio

```bash
git branch
```
Muestra todas las ramas disponibles en el repositorio.

## Crear una Nueva Rama y Cambiar a Ella

```bash
git checkout -b <nombre_rama>
```
Crea una nueva rama y cambia a ella automáticamente.

## Cambiar a Otra Rama Existente

```bash
git switch <nombre_rama>
```
Permite cambiar a una rama específica dentro del repositorio.

## Subir Cambios a una Rama en el Repositorio Remoto

```bash
git push origin <nombre_rama>
```
Sube los cambios realizados en la rama especificada al repositorio remoto.

## Fusionar Ramas

```bash
git merge <nombre_rama>
```
Une los cambios de la rama especificada con la rama actual.

## Hacer Push a una Rama Específica

```bash
git push origin <nombre_rama>
```
Envía los cambios realizados en la rama al repositorio remoto.

---

Este documento sirve como una referencia rápida para trabajar con Git de manera eficiente.

