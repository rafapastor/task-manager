export interface Task {
  id?: number;
  title: string;
  description: string;
  dueDate: string;
  status?: string;
}

interface TaskListData {
  tasks: Task[];
  error: string;
}

interface TaskData {
  task: Task;
  errors: Record<string, string>;
  serverError: string;
}
