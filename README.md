# thread-main
Основное задание по курсу Thread.
Реализован вариант задания, в котором каждый поток может изменять любой элемент матрицы, а не только диагональный.
При этом каждый поток минимум должен изменить Worker.MIN_MODIFIED_FIELDS_COUNT элементов матрицы. 
Для решения этой задачи были использованы библиотеки java.util.concurrent, java.util.concurrent.locks.
