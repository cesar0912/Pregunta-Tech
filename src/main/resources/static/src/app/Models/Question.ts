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

export function clearQuestionInterface(
  preguntaActual: Question,
  categoryInput: string,
  levelInput: string
): Question {
  return (preguntaActual = {
    id: '',
    category: categoryInput,
    level: levelInput,
    question: '',
    answers: {},
    correct_answer: '',
    feedback: '',
  });
}
