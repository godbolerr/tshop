import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { TResource } from './t-resource.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class TResourceService {

    private resourceUrl = 'api/t-resources';

    constructor(private http: Http) { }

    create(tResource: TResource): Observable<TResource> {
        const copy = this.convert(tResource);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(tResource: TResource): Observable<TResource> {
        const copy = this.convert(tResource);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: string): Observable<TResource> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: string): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(tResource: TResource): TResource {
        const copy: TResource = Object.assign({}, tResource);
        return copy;
    }
}
