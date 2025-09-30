public class Helpers {

    // check status for epic
    public static void recheckEpic(Epic epic){
        if(!epic.subtasks.isEmpty()){
            return;
        }
        int allNew = 1;
        int allDone = 1;

        for(Subtask subtask : epic.subtasks.values()){
            if(subtask.status == Status.IN_PROGRESS) {
                epic.status = Status.IN_PROGRESS;
                allNew = 0;
                allDone = 0;
            } else if(subtask.status == Status.DONE) {
                allNew = 0;
            } else if(subtask.status == Status.NEW) {
                allDone = 0;
            }
            if(allNew == 1) epic.status = Status.NEW;
            else if(allDone == 1) epic.status = Status.DONE;
        }

    }
}
