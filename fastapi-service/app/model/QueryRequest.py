from pydantic import BaseModel

class QueryRequest(BaseModel):
    text: str
    top_n: int = 5
