package red.mohist;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    static Map<String, CommandExecutor> cmds = new HashMap<>();

    public static void call(Command command) {
        if (cmds.containsKey(command.getLabel())) {
            cmds.get(command.getLabel()).onCommand(command);
        }
    }

    public static void register(String label, CommandExecutor commandExecutor) {
        cmds.put(label, commandExecutor);
    }

    public static void register(String label, String[] alias, CommandExecutor commandExecutor) {
        cmds.put(label, commandExecutor);
        for (String alia : alias) cmds.put(label, commandExecutor);
    }
}
