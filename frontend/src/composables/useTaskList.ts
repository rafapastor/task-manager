import { ref } from "vue";
import axios from "axios";
import { TaskListData } from "@/types/types";

export function useTaskList() {
  const taskListData = ref<TaskListData>({
    tasks: [],
    error: "",
  });

  const fetchTasks = async () => {
    try {
      const response = await axios.get("http://localhost:8080/tasks");
      taskListData.value.tasks = response.data;
    } catch (error: any) { // eslint-disable-line
      taskListData.value.error = "An error occurred while fetching tasks.";
    }
  };

  const deleteTask = async (id: number) => {
    try {
      await axios.delete(`http://localhost:8080/tasks/${id}`);
      taskListData.value.tasks = taskListData.value.tasks.filter(
        (task) => task.id !== id
      );
    } catch (error: any) { // eslint-disable-line
      taskListData.value.error = "An error occurred while deleting the task.";
    }
  };

  return {
    taskListData,
    fetchTasks,
    deleteTask,
  };
}
