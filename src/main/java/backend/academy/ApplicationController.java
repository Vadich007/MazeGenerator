package backend.academy;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ApplicationController {
    private BaseGenerator generator;
    private BaseSolver solver;
    private final BaseRenderer renderer;
    private final Scanner scanner;
    private final PrintStream ps;

    ApplicationController(InputStream in, OutputStream out, BaseRenderer renderer) {
        this.renderer = renderer;
        this.scanner = new Scanner(System.in);
        this.ps = new PrintStream(System.out);
    }

    public void start() {
        chooseGenerator();
        Maze maze = null;
        while (maze == null) {
            maze = generateMaze();
        }
        chooseSolver();
        Coordinate start = null;
        Coordinate end = null;
        while (start == null) {
            start = readCoordinate(maze.height(), maze.width(), "начальной");
        }
        while (end == null) {
            end = readCoordinate(maze.height(), maze.width(), "конечной");
        }
        scanner.close();
        var path = solver.solve(maze, start, end);
        ps.println();
        ps.println("Исходный лабиринт");
        ps.println();

        renderer.render(maze);

        ps.println();
        ps.println("Кратчайший путь между введенными точками");
        ps.println();

        renderer.render(maze, path);
    }

    @SuppressWarnings("MultipleStringLiterals")
    private Coordinate readCoordinate(int height, int weight, String type) {
        ps.println("Введите координаты " + type + " точки через пробел (\"х\" \"у\")");
        var params = scanner.nextLine().split(" ");
        Coordinate coordinate = null;
        try {
            int x = Integer.parseInt(params[0]);
            int y = Integer.parseInt(params[1]);
            if (params.length == 2 && height > x && weight > y) {
                coordinate = new Coordinate(x, y);
            }
        } catch (Exception ex) {
            ps.println("Неверный ввод попробуйте еще раз");
        }
        return coordinate;
    }

    @SuppressWarnings("MultipleStringLiterals")
    private void chooseSolver() {
        ps.println("Выберите алгоритм поиска пути");
        ps.println("1 - алгоритм Дейкстры");
        ps.println("2 - алгоритм \"поиск в ширину\"");
        ps.println("или любой другой символ для алгоритма по умолчанию");
        if (scanner.nextLine().equals("1")) {
            solver = new DijkstrasSolver();
        } else {
            solver = new BfsSolver();
        }
    }

    private void chooseGenerator() {
        ps.println("Выберите алгоритм генерации");
        ps.println("1 - алгоритм Эйлера");
        ps.println("2 - алгоритм Краскала");
        ps.println("или любой другой символ для алгоритма по умолчанию");
        if (scanner.nextLine().equals("1")) {
            generator = new EulerGenerator();
        } else {
            generator = new KruskalsGenerator();
        }
    }

    private Maze generateMaze() {
        ps.println("Введите параметры размера лабиринта через пробел (\"ширина\" \"высота\")");
        var params = scanner.nextLine().split(" ");
        Maze maze = null;
        try {
            int height = Integer.parseInt(params[0]);
            int weight = Integer.parseInt(params[1]);
            if (params.length == 2 && height > 0 && weight > 0) {
                maze = generator.generate(height, weight);

            }
        } catch (Exception ex) {
            ps.println("Неверный ввод попробуйте еще раз");
        }
        return maze;
    }
}
