export interface QuestionExam {
  id: number;
  category: string;
  level: string;
  question: string;
  answers: string[];
  correct_answer: string;
  feedback?: string;
}
