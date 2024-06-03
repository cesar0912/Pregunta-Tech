export interface Question {
  id: string;
  category: string;
  level: string;
  question: string;
  answers: {
    [key: string]: string;
  };
  correct_answer: string;
  feedback?: string;
}
