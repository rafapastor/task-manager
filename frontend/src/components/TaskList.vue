<template>
  <div>
    <h1 class="text-2xl font-bold mb-4">Task List</h1>
    <table class="min-w-full bg-white">
      <thead class="bg-gray-800 text-white">
        <tr>
          <th class="w-1/3 py-2">Title</th>
          <th class="w-1/3 py-2">Description</th>
          <th class="w-1/6 py-2">Due Date</th>
          <th class="w-1/6 py-2">Status</th>
          <th class="w-1/6 py-2">Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="task in tasks" :key="task.id" class="text-center">
          <td class="border px-4 py-2">{{ task.title }}</td>
          <td class="border px-4 py-2">{{ task.description }}</td>
          <td class="border px-4 py-2">{{ task.dueDate }}</td>
          <td class="border px-4 py-2">
            {{ task.completed ? "Completed" : "Pending" }}
          </td>
          <td class="border px-4 py-2">
            <button
              @click="deleteTask(task.id)"
              class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
            >
              Delete
            </button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import axios from "axios";

interface Task {
  id: number;
  title: string;
  description: string;
  dueDate: string;
  completed: boolean;
}

export default defineComponent({
  data() {
    return {
      tasks: [] as Task[],
    };
  },
  created() {
    this.fetchTasks();
  },
  methods: {
    fetchTasks() {
      axios
        .get<Task[]>("http://localhost:8080/tasks")
        .then((response) => {
          this.tasks = response.data;
        })
        .catch((error) => {
          console.error("There was an error fetching the tasks!", error);
        });
    },
    deleteTask(id: number) {
      axios
        .delete(`http://localhost:8080/tasks/${id}`)
        .then((response) => {
          this.fetchTasks();
        })
        .catch((error) => {
          console.error("There was an error deleting the task!", error);
        });
    },
  },
});
</script>
