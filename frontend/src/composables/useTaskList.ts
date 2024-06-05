import { reactive } from "vue";
import axios from "axios";

interface Task {
  id: number;
  title: string;
  description: string;
  dueDate: string;
  status: string;
}

interface TaskListData {
  tasks: Task[];
  error: string;
}

export function useTaskList() {
  const taskListData = reactive<TaskListData>({
    tasks: [],
    error: "",
  });

  const fetchTasks = async () => {
    try {
      const response = await axios.get("http://localhost:8080/tasks");
      taskListData.tasks = response.data;
    } catch (error: any) {
      taskListData.error = "An error occurred while fetching tasks.";
    }
  };

  const deleteTask = async (id: number) => {
    try {
      await axios.delete(`http://localhost:8080/tasks/${id}`);
      taskListData.tasks = taskListData.tasks.filter((task) => task.id !== id);
    } catch (error: any) {
      taskListData.error = "An error occurred while deleting the task.";
    }
  };

  return {
    taskListData,
    fetchTasks,
    deleteTask,
  };
}
