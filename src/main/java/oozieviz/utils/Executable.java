package oozieviz.utils;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public final class Executable {

    private final File exe;
    private String[] args;

    public Executable(File exe) {
        this.exe = exe;
    }

    public Executable withArguments(String... args) {
        this.args = args;
        return this;
    }

    public Executable run() throws Exception {
        List<String> commands = new LinkedList<>();
        commands.add(exe.getAbsolutePath());
        commands.addAll(Arrays.asList(args));
        ProcessBuilder pb = new ProcessBuilder(commands);
        pb.redirectErrorStream(true);
        Process p = pb.start();
        int exitValue = p.waitFor();
        if (exitValue != 0) {
            throw new ExecutionException(String.format("Failed executing command: %s", String.join(" ", pb.command())));
        }
        return this;
    }

}
