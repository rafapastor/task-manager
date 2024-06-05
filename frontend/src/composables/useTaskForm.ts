import { reactive } from "vue";
import axios from "axios";
import { TaskData } from "@/types/types";

export function useTaskForm() {
  const taskData = reactive<TaskData>({
    task: {
      title: "",
      description: "",
      dueDate: "",
      status: "pending",
    },
    errors: {},
    serverError: "",
  });

  const submitTaskForm = async (id?: number): Promise<boolean> => {
    try {
      taskData.errors = {}; // Clear previous errors
      taskData.serverError = ""; // Clear previous server errors
      if (id) {
        await axios.put(`http://localhost:8080/tasks/${id}`, taskData.task);
      } else {
        const { status, ...taskWithoutStatus } = taskData.task; // Remove status field for new tasks
        await axios.post("http://localhost:8080/tasks", taskWithoutStatus);
      }
      return true;
    } catch (error: any) {
      if (error.response && error.response.status === 400) {
        taskData.errors = error.response.data; // Handle validation errors
      } else {
        taskData.serverError =
          "An error occurred while saving the task. Please try again later."; // Handle other errors
      }
      return false;
    }
  };

  return {
    taskData,
    submitTaskForm,
  };
}
