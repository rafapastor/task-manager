import { reactive } from "vue";
import axios from "axios";

interface Task {
  title: string;
  description: string;
  dueDate: string;
}

interface TaskData {
  task: Task;
  errors: Record<string, string>;
  serverError: string;
}

export function useTaskForm() {
  const taskData = reactive<TaskData>({
    task: {
      title: "",
      description: "",
      dueDate: "",
    },
    errors: {},
    serverError: "",
  });

  const submitTaskForm = async () => {
    try {
      taskData.errors = {}; // Clear previous errors
      taskData.serverError = ""; // Clear previous server errors
      const response = await axios.post(
        "http://localhost:8080/tasks",
        taskData.task
      );
      console.log(response.data); // Handle success (e.g., redirect or clear form)
      // Optionally, you can reset the form fields here
      taskData.task.title = "";
      taskData.task.description = "";
      taskData.task.dueDate = "";
    } catch (error: any) {
      if (error.response && error.response.status === 400) {
        taskData.errors = error.response.data; // Handle validation errors
      } else {
        taskData.serverError =
          "An error occurred while adding the task. Please try again later."; // Handle other errors
      }
    }
  };

  return {
    taskData,
    submitTaskForm,
  };
}
