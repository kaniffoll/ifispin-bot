from fastapi import APIRouter
from app.model.QueryRequest import QueryRequest

router = APIRouter()

search_service = None

def init_service(service):
    global search_service
    search_service = service

@router.post("/search")
def search(req: QueryRequest):
    return {
        "results": search_service.search(req.text, req.top_n)
    }
